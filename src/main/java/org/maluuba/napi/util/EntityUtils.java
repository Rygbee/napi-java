package org.maluuba.napi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonNode;
import org.maluuba.napi.client.DateRange;
import org.maluuba.napi.client.TimeRange;

public class EntityUtils {
  private final static DateFormat DATE_RANGE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
  private final static DateFormat TIME_RANGE_FORMATTER = new SimpleDateFormat("hh:mm:ssa");
  
  private final static Logger log = Logger.getLogger(EntityUtils.class.getName());
  
  public static Class<?> getValueTypeForEntity(String entityName) {
    if (entityName.equals("timeRange")) {
      return JsonNode.class;
    } else if (entityName.equals("dateRange")) {
      return JsonNode.class;
    } else if (entityName.equals("contacts")) {
      return JsonNode.class;
    } else {
      return String.class;
    }
  }
  
  public static DateRange convertDateRange(Map<String, String> node) {
    try {
      return new DateRange(
          DATE_RANGE_FORMATTER.parse(node.get("start")),
          DATE_RANGE_FORMATTER.parse(node.get("end")));
    } catch (ParseException pe) {
      log.log(Level.WARNING, String.format("Failed to format date range: %s-%s", node.get("start"), node.get("end")));
      return null;
    }
  }
  
  public static TimeRange convertTimeRange(Map<String, String> node) {
    try {
      return new TimeRange(
          TIME_RANGE_FORMATTER.parse(node.get("start")),
          TIME_RANGE_FORMATTER.parse(node.get("end")));
    } catch (ParseException pe) {
      log.log(Level.WARNING, String.format("Failed to format time range: %s-%s", node.get("start"), node.get("end")));
      return null;
    }
  }
  
  @SuppressWarnings("unchecked")
  public static Map<String, List<Object>> convertEntities(Map<String, List<Object>> entities) {
    Map<String, List<Object>> convertedEntities = new HashMap<String, List<Object>>();
    for (Map.Entry<String, List<Object>> entity : entities.entrySet()) {
      if (entity.getKey().equals("dateRange")) {
        convertedEntities.put(entity.getKey(), new LinkedList<Object>());
        for (Object value : entity.getValue()) {
          convertedEntities.get(entity.getKey()).add(convertDateRange((Map<String, String>) value));
        }
      } else if (entity.getKey().equals("timeRange")) {
        convertedEntities.put(entity.getKey(), new LinkedList<Object>());
        for (Object value : entity.getValue()) {
          convertedEntities.get(entity.getKey()).add(convertTimeRange((Map<String, String>) value));
        }
      } else {
        convertedEntities.put(entity.getKey(), entity.getValue());
      }
    }
    return convertedEntities;
  }
}
