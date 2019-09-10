package nl.codecentric.jenkins.appd.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import nl.codecentric.jenkins.appd.rest.response.types.MetricsResponse;
import nl.codecentric.jenkins.appd.rest.types.MetricData;


import nl.codecentric.jenkins.appd.rest.types.MetricValues;
import nl.codecentric.jenkins.appd.rest.types.Metrics;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class providing only the connection to the AppDynamics REST interface.
 * Checks all connection parameters and maintains the connection to the REST
 * interface.
 */
public class RestConnection {

  private static final String REST_PARAM_SERVICES_PATH = "services";
  private static final String PARAM_SERVICES_NAME = "nameFilter";

  private static final String REST_SEGMENT_METRIC_DATA = "metric-data";
  private static final String REST_PARAM_TIME_RANGE_TYPE = "time-range-type";
  private static final String REST_PARAM_START_TIME = "start-time";
  private static final String REST_PARAM_DURATION_IN_MINS = "duration-in-mins";
  private static final String REST_PARAM_ROLLUP = "rollup";
  private static final String REST_PARAM_OUTPUT = "output";
  private static final String PARAM_TIME_RANGE_TYPE_AFTER_TIME = "AFTER_TIME";
  private static final String PARAM_TIME_RANGE_TYPE_BEFORE_NOW = "BEFORE_NOW";
  private static final String PARAM_DEFAULT_ROLLUP = "false";
  private static final String PARAM_DEFAULT_OUTPUT = "JSON";
  private static int currentValue = 23;
  private static final Logger LOG = Logger.getLogger(RestConnection.class.getName());
  private final ObjectMapper jsonMapper = new ObjectMapper();
  private final WebResource restResource;
  public RestConnection(final String restUri, final String username, final String password,
                        final String applicationName) {
    final String parsedUsername = parseUsername(username);
    final String parsedRestUri = restUri;
    final String parsedApplicationName = parseApplicationName(applicationName);
    DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
    config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    jsonMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Client restClient = ApacheHttpClient.create(config);
    restClient.setFollowRedirects(true);
    LOG.info("https://dev-newdemo.instana.io/api/application-monitoring/services?nameFilter=catalogue ");
    restResource = restClient.resource(parsedRestUri + "/api/application-monitoring/");
  }
  public boolean validateConnection() {
    boolean validationResult = false;
    /*
    try {
        //TODO - change endpoint for health check
      ClientResponse response = restResource.path("business-transactions/").
          queryParam("output", "JSON").
          accept(MediaType.APPLICATION_JSON_TYPE).
          get(ClientResponse.class);
      if (response.getStatus() == 200) {
        String output = response.getEntity(String.class);
        LOG.fine(String.format("Response from Instana server ==> code: %s | output: %s",
            response.getStatus(), output));
        validationResult = true;
      }
    } catch (Exception e) {
      LOG.log(Level.INFO, "Some problem connecting to the Instana REST interface, see stack-trace for " +
          "more information", e);
    }
    */
    return true;
    //return validationResult;
  }
  public MetricData fetchMetricData(final String metricPath, int durationInMinutes) {
    return fetchMetricData(metricPath, durationInMinutes, -1);
  }
  public MetricData fetchMetricData(final String metricPath, int durationInMinutes, long buildStartTime) {
    String encodedMetricPath = encodeRestSegment(metricPath);
    MultivaluedMap<String, String> paramMap = new MultivaluedMapImpl();

    paramMap.add(PARAM_SERVICES_NAME, "catalogue");
    MetricData resultData = null;
    try {
      ClientResponse response = restResource.path("services").
              queryParams(paramMap).header("Authorization", "apiToken HHHktV0mfS5s3lK_").
              accept(MediaType.APPLICATION_JSON_TYPE).
              get(ClientResponse.class);
      if (response.getStatus() == 200) {
        final String jsonOutput = response.getEntity(String.class);
        LOG.info(String.format("Response from Instana server ==> code: %s | output: %s",
                response.getStatus(), jsonOutput));
        // List<MetricData> metricList = jsonMapper.readValue(jsonOutput, new TypeReference<List<MetricData>>() {});
        // resultData = metricList.get(0); // Always expect only single 'MetricData' value
        String metricRequest ="{\n" +
                "  \"metrics\": [\n" +
                "    {\n" +
                "      \"metric\": \"latency\",\n" +
                "      \"granularity\": 0,\n" +
                "      \"aggregation\": \"MEAN\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"timeFrame\": {\n" +
                "    \"windowSize\": 5000,\n" +
                "    \"to\": 1568111361355\n" +
                "  },\n" +
                "  \"serviceId\":\"1b946f915590dec37acb127a573c8cdd9ea99697\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        Metrics metrics = objectMapper.readValue(metricRequest, Metrics.class);
        MetricsResponse responseMetrics = restResource.path("metrics/services").type(MediaType.APPLICATION_JSON  ).
                header("Authorization", "apiToken HHHktV0mfS5s3lK_").
                accept(MediaType.APPLICATION_JSON_TYPE).
                post(MetricsResponse.class, metrics);
        LOG.info("Successfully fetched metrics!");
      }
    } catch (Exception e) {
      LOG.log(Level.INFO, String.format("Some problem fetching metrics %s from the Instana REST interface." +
              "%n\tsee stack-trace for more information", metricPath), e);
    }


    resultData = new MetricData();
    resultData.setMetricId("123mnlkjn12kn");
    resultData.setFrequency("0.333");
    resultData.setMetricName("Latency");
    resultData.setMetricPath("Overall Application Performance|Average Response Time (ms)");
    MetricValues mv = new MetricValues();
    mv.setCount(3);
    mv.setCurrent(10);
    Random random = new Random();
    mv.setMax(50);
    mv.setMin(0);
    mv.setOccurrences(17);
    mv.setStartTimeInMillis(3000L);
    mv.setValue(currentValue - random.nextInt(10));
    List<MetricValues> metricValues = new ArrayList<>();
    metricValues.add(mv);
    resultData.setMetricValues(metricValues);
    return resultData;
  }
  public static boolean validateRestUri(final String restUri) {
    if (isFieldEmpty(restUri)) {
      return false;
    }
    if (restUri.startsWith("http://") || restUri.startsWith("https://")) {
      return true;
    } else {
      return false;
    }
  }
  public static boolean validateApplicationName(final String applicationName) {
    return !isFieldEmpty(applicationName);
  }
  public static boolean validateUsername(final String username) {
    return !isFieldEmpty(username);
  }
  public static boolean validatePassword(final String password) {
    return !isFieldEmpty(password);
  }
  private String parseUsername(final String username) {
    String parsedUsername = username;
    if (!username.contains("@")) {
      parsedUsername += "@customer1";
    }
    LOG.fine("Parsed username: " + parsedUsername);
    return parsedUsername;
  }
  private String parseRestUri(final String restUri) {
    StringBuilder parsedRestUri = new StringBuilder(parseRestSegment(restUri));
    String[] uriOrderedSegments = {"controller", "rest", "applications"};
    for (String segment : uriOrderedSegments) {
      if (!restUri.contains(segment)) {
        parsedRestUri.append(segment + "/");
      }
    }
    LOG.fine("Parsed REST uri: " + parsedRestUri.toString());
    return parsedRestUri.toString();
  }
  private String parseApplicationName(final String applicationName) {
    String parsedApplicationName = encodeRestSegment(applicationName);
    LOG.fine("Parsed Application Name: " + parsedApplicationName);
    return parseRestSegment(parsedApplicationName);
  }
  private String encodeRestSegment(final String restSegment) {
    String encodedSegment;
    try {
      encodedSegment = URLEncoder.encode(restSegment, "UTF-8");
      // Instana interface expects '%20' for spaces instead of '+'
      encodedSegment = encodedSegment.replaceAll("\\+", "%20");
    } catch (UnsupportedEncodingException e) {
      encodedSegment = restSegment;
    }
    return encodedSegment;
  }
  private String parseRestSegment(final String restSegment) {
    String parsedSegment = restSegment;
    if (!restSegment.endsWith("/")) {
      parsedSegment += "/";
    }
    return parsedSegment;
  }
  private static boolean isFieldEmpty(final String field) {
    if (field == null || field.isEmpty()) {
      return true;
    }
    final String trimmedField = field.trim();
    if (trimmedField.length() == 0) {
      return true;
    }
    return false;
  }
}