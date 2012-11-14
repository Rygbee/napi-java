package com.maluuba.napi.client;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.maluuba.napi.client.InterpretRequest;
import com.maluuba.napi.client.InterpretResponse;
import com.maluuba.napi.client.MaluubaNAPIClient;
import com.maluuba.napi.client.NAPIAction;
import com.maluuba.napi.client.NAPICategory;

import static com.maluuba.napi.client.NAPIAction.*;
import static com.maluuba.napi.client.NAPICategory.*;

/**
 * A set of tests for each individual Action.
 * 
 * Make sure to provide a {@code MaluubaCredentials.properties} file on the classpath
 * with an appropriate API_KEY property in order for these tests to pass.
 */
public class MaluubaNAPIClientTest {
  private MaluubaNAPIClient client;
  
  @Before
  public void setUp() throws IOException {
    Properties p = new Properties();
    p.load(this.getClass().getResourceAsStream("/MaluubaCredentials.properties"));
    client = new MaluubaNAPIClient(p.getProperty("API_KEY"));
  }
  
  private InterpretResponse checkPhrase(String phrase, NAPICategory category, NAPIAction action) throws IOException {
    InterpretResponse response = client.interpret(new InterpretRequest(phrase));
    Assert.assertEquals(category, response.getCategory());
    Assert.assertEquals(action, response.getAction());
    return response;
  }

  private void checkPhrase(String phrase, NAPICategory category, NAPIAction action, String key1, Object value1) throws IOException {
    InterpretResponse response = checkPhrase(phrase, category, action);
    Assert.assertTrue(response.getEntities().containsKey(key1));

    Assert.assertEquals(response.getEntities().get(key1).get(0), value1);
  }

  private void checkPhrase(String phrase, NAPICategory category, NAPIAction action, String key1, Object value1, String key2, Object value2) throws IOException {
    InterpretResponse response = checkPhrase(phrase, category, action);
    Assert.assertTrue(response.getEntities().containsKey(key1));
    Assert.assertTrue(response.getEntities().containsKey(key2));
    
    Assert.assertEquals(response.getEntities().get(key1).get(0), value1);
    Assert.assertEquals(response.getEntities().get(key2).get(0), value2);
  }


  private void checkPhrase(String phrase, NAPICategory category, NAPIAction action, String key1, Object value1, String key2, Object value2, String key3, Object value3) throws IOException {
    InterpretResponse response = checkPhrase(phrase, category, action);
    Assert.assertTrue(response.getEntities().containsKey(key1));
    Assert.assertTrue(response.getEntities().containsKey(key2));
    Assert.assertTrue(response.getEntities().containsKey(key3));
    
    Assert.assertEquals(response.getEntities().get(key1).get(0), value1);
    Assert.assertEquals(response.getEntities().get(key2).get(0), value2);
    Assert.assertEquals(response.getEntities().get(key3).get(0), value3);
  }

  @Test
  public void test_businessSearch() throws IOException {
    checkPhrase("where can I buy a hammer", BUSINESS, BUSINESS_SEARCH, "searchTerm", "hammer");
    checkPhrase("i am hungry", BUSINESS, BUSINESS_SEARCH, "searchTerm", "hungry");
    checkPhrase("i want some pizza", BUSINESS, BUSINESS_SEARCH, "searchTerm", "pizza");
  }
  
  @Test
  public void test_businesReservation() throws IOException {
    checkPhrase("call josh", CALL, CALL_DIAL);
    checkPhrase("dial pizza hut", CALL, CALL_DIAL);
  }
  
  @Test
  public void test_callCheckMissed() throws IOException {
    checkPhrase("did I miss any calls", CALL, CALL_CHECK_MISSED);
  }
  
  @Test
  public void test_callRespondMissed() throws IOException {
    checkPhrase("respond to that missed call", CALL, CALL_RESPOND_MISSED);
  }
  
  @Test
  public void test_callAcceptIncoming() throws IOException {
    checkPhrase("accept this call" ,CALL, CALL_ACCEPT_INCOMING);
  }
  
  @Test
  public void test_contactAdd() throws IOException {
    checkPhrase("add josh 5551234", CONTACT, CONTACT_ADD, "contactFieldValue", "5551234");
  }
  
  @Test
  public void test_contactSearch() throws IOException {
    checkPhrase("what is josh's phone number", CONTACT, CONTACT_SEARCH, "contactField", "phone number");
    checkPhrase("show me adrian's information", CONTACT, CONTACT_SEARCH);
  }
  
  @Test
  public void test_contactSetAlias() throws IOException {
    checkPhrase("elizabeth is my mom", CONTACT, CONTACT_SET_ALIAS);
  }
  
  @Test
  public void test_knowledgeSearch() throws IOException {
    checkPhrase("who is Barack Obama", KNOWLEDGE, KNOWLEDGE_SEARCH);
    checkPhrase("who is the president", KNOWLEDGE, KNOWLEDGE_SEARCH);
    checkPhrase("what is two plus two", KNOWLEDGE, KNOWLEDGE_SEARCH);
    checkPhrase("what is the tallest mountain", KNOWLEDGE, KNOWLEDGE_SEARCH);
  }
  
  @Test
  public void test_entertainmentMovie() throws IOException {
    checkPhrase("I want to see a funny movie", ENTERTAINMENT, NAPIAction.ENTERTAINMENT_MOVIE, "genre", "comedy");
    checkPhrase("I want to see skyfall", ENTERTAINMENT, ENTERTAINMENT_MOVIE, "title", "skyfall");
  }
  
  @Test
  public void test_entertainmentEvent() throws IOException {
    checkPhrase("when do the leafs play", ENTERTAINMENT, ENTERTAINMENT_EVENT, "event", "leafs");
    checkPhrase("I want to see justin bieber", ENTERTAINMENT, ENTERTAINMENT_EVENT, "event", "justin bieber");
  }
  
  @Test
  public void test_entertainmentAmbiguous() throws IOException {
    checkPhrase("what's fun to do on the weekend", ENTERTAINMENT, ENTERTAINMENT_AMBIGUOUS);
  }
  
  @Test
  public void test_emailSend() throws IOException {
    checkPhrase("email adrian about the api", EMAIL, EMAIL_SEND);
  }
  
  @Test
  public void test_emailDisplay() throws IOException {
    checkPhrase("show me emails from jsoh", EMAIL, EMAIL_DISPLAY);
  }
  
  @Test
  public void test_helpHelp() throws IOException {
    checkPhrase("help", HELP, HELP_HELP);
  }
  
  @Test
  public void test_travelFlight() throws IOException {
    checkPhrase("i would like a first class ticket to new york leaving from toronto on the day before christmas returning a week after christmas", TRAVEL, NAPIAction.TRAVEL_FLIGHT);
  }
  
  @Test
  public void test_musicPlay() throws IOException {
    checkPhrase("play the song firework", MUSIC, MUSIC_PLAY, "title", "firework");
  }
  
  @Test
  public void test_musicPause() throws IOException {
    checkPhrase("please pause the music", MUSIC, MUSIC_PAUSE);
  }
  
  @Test
  public void test_calendarCreateEvent() throws IOException {
    checkPhrase("Set up a meeting from 8 to 10", CALENDAR, CALENDAR_CREATE_EVENT, "title", "meeting");
  }
  
  @Test
  public void test_calendarSearch() throws IOException {
    checkPhrase("what meetings do I have on Friday", CALENDAR, CALENDAR_SEARCH, "title", "meetings");
  }
  
  @Test
  public void test_calendarRemoveEvent() throws IOException {
    checkPhrase("Cancel my next meeting", CALENDAR, CALENDAR_REMOVE_EVENT, "title", "meeting");
  }
  
  @Test
  public void test_calendarModifyEvent() throws IOException {
    checkPhrase("Move my 5 o'clock to 7", CALENDAR, CALENDAR_MODIFY_EVENT);
  }
  
  @Test
  public void test_calendarAvailability() throws IOException {
    checkPhrase("When am I available", CALENDAR, CALENDAR_AVAILABILITY);
  }
  
  @Test
  public void test_weatherStatus() throws IOException {
    checkPhrase("What is the weather outside?", WEATHER, WEATHER_STATUS);
  }
  
  @Test
  public void test_weatherDetails() throws IOException {
    checkPhrase("What is the wind speed?", WEATHER, WEATHER_DETAILS);
  }
  
  @Test
  public void test_weatherSunset() throws IOException {
    checkPhrase("When is the sunset?", WEATHER, WEATHER_SUNSET);
  }
  
  @Test
  public void test_weatherSunrise() throws IOException {
    checkPhrase("When is sunrise for Friday?", WEATHER, WEATHER_SUNRISE);
  }
  
  @Test
  public void test_reminderSet() throws IOException {
    checkPhrase("Remind me to put out the garbage tonight", REMINDER, REMINDER_SET, "message", "put out the garbage", "time", "11:00:00PM");
  }
  
  @Test
  public void test_reminderSearch() throws IOException {
    checkPhrase("Find me reminders for this week", REMINDER, REMINDER_SEARCH);
  }
  
  @Test
  public void test_alarmSet() throws IOException {
    checkPhrase("set an alarm for 5 30", ALARM, ALARM_SET);
  }
  
  @Test
  public void test_alarmSetRecurring() throws IOException {
    checkPhrase("set an alarm at 5 30 every morning", ALARM, ALARM_SET_RECURRING);
  }
  
  @Test
  public void test_alarmModify() throws IOException {
    checkPhrase("Change my morning alarms from 5 to 7", ALARM, ALARM_MODIFY);
  }
  
  @Test
  public void test_alarmCancel() throws IOException {
    checkPhrase("Cancel my alarm at 6 tonight", ALARM, ALARM_CANCEL);
  }
  
  @Test
  public void test_alarmCancelAllAlarms() throws IOException {
    checkPhrase("Remove all my alarms", ALARM, ALARM_CANCEL_ALL_ALARMS);
  }
  
  @Test
  public void test_alarmSearch() throws IOException {
    checkPhrase("Find my alarms", ALARM, ALARM_SEARCH);
  }
  
  @Test
  public void test_timerStart() throws IOException {
    checkPhrase("Set a 30 minute timer", TIMER, TIMER_START);
  }
  
  @Test
  public void test_timerDisplay() throws IOException {
    checkPhrase("show my timer", TIMER, TIMER_DISPLAY);
  }
  
  @Test
  public void test_timerCancel() throws IOException {
    checkPhrase("cancel the timer", TIMER, TIMER_CANCEL);
  }
  
  @Test
  public void test_timerPause() throws IOException {
    checkPhrase("pause timer", TIMER, TIMER_PAUSE);
  }

  @Test
  public void test_stopwatchStop() throws IOException {
    checkPhrase("stop a stopwatch", STOPWATCH, STOPWATCH_STOP);
  }
  
  @Test
  public void test_stopwatchDisplay() throws IOException {
    checkPhrase("display the stopwatch", STOPWATCH, STOPWATCH_DISPLAY);
  }
  
  @Test
  public void test_navigationDirections() throws IOException {
    checkPhrase("How do I get to the mall from my house", NAVIGATION, NAVIGATION_DIRECTIONS, "departing", "house", "destination", "mall");
    checkPhrase("How do I get to san francisco", NAVIGATION, NAVIGATION_DIRECTIONS, "destination", "san francisco");
  }
  
  @Test
  public void test_navigationWhereAmI() throws IOException {
    checkPhrase("Show my current location", NAVIGATION, NAVIGATION_WHERE_AM_I);
  }
  
  @Test
  public void test_transitNextBus() throws IOException {
    checkPhrase("When will the next bus come to the university", TRANSIT, TRANSIT_NEXT_BUS, "departing", "university", "transitType", "bus");
  }
  
  @Test
  public void test_transitNearbyStops() throws IOException {
    checkPhrase("bus stops near the mall", TRANSIT, TRANSIT_NEARBY_STOPS, "departing", "mall", "transitType", "bus", "destination", "mall");
  }
  
  @Test
  public void test_transitSchedule() throws IOException {
    checkPhrase("What is the schedule for the green route tomorrow", TRANSIT, TRANSIT_SCHEDULE, "route", "green");
  }

  @Test
  public void test_searchAmazon() throws IOException {
    checkPhrase("i want to buy a book on amazon", SEARCH, SEARCH_AMAZON, "searchTerm", "book");
    checkPhrase("search amazon for electronics", SEARCH, SEARCH_AMAZON, "searchTerm", "electronics");
  }

  @Test
  public void test_searchBing() throws IOException {
    checkPhrase("bing search ryan seacrest", SEARCH, SEARCH_BING, "searchTerm", "ryan seacrest");
  }

  @Test
  public void test_searchEbay() throws IOException {
    checkPhrase("search ebay for socks", SEARCH, SEARCH_EBAY, "searchTerm", "socks");
  }

  @Test
  public void test_searchDefault() throws IOException {
    checkPhrase("search the web for cheese", SEARCH, SEARCH_DEFAULT, "searchTerm", "cheese");
  }

  @Test
  public void test_searchGoogle() throws IOException {
    checkPhrase("google search androids", SEARCH, SEARCH_GOOGLE, "searchTerm", "androids");
  }
  
  @Test
  public void test_searchRecipes() throws IOException {
    checkPhrase("how do i make butter chicken", SEARCH, SEARCH_RECIPES, "searchTerm", "butter chicken");
  }
  
  @Test
  public void test_searchWikipedia() throws IOException {
    checkPhrase("search wikipedia for the romans", SEARCH, SEARCH_WIKIPEDIA, "searchTerm", "romans");
  }
  
  @Test
  public void test_textDisplay() throws IOException {
    checkPhrase("show unread texts", TEXT, TEXT_DISPLAY);
  }
  
  @Test
  public void test_textSend() throws IOException {
    checkPhrase("send a text to rob how is the law stuff", TEXT, TEXT_SEND, "message", "how is the law stuff");
  }

  @Test
  public void test_socialFacebookSendMessage() throws IOException {
    checkPhrase("send a facebook message to zhiyuan hey g", SOCIAL, SOCIAL_FACEBOOK_SEND_MESSAGE);
  }

  @Test
  public void test_socialFaacebookShowNewsfeed() throws IOException {
    checkPhrase("show me my facebook newsfeed", SOCIAL, SOCIAL_FACEBOOK_SHOW_NEWSFEED);
  }

  @Test
  public void test_socialFacebookShowPhotos() throws IOException {
    checkPhrase("show me pictures of irene", SOCIAL, SOCIAL_FACEBOOK_SHOW_PHOTOS);
  }

  @Test
  public void test_socialFacebookShowWall() throws IOException {
    checkPhrase("take me to cynthia's facebook wall", SOCIAL, SOCIAL_FACEBOOK_SHOW_WALL);
  }

  @Test
  public void test_socialFacebookWriteOnWall() throws IOException {
    checkPhrase("write on sam's wall good luck in korea", SOCIAL, SOCIAL_FACEBOOK_WRITE_ON_WALL, "message", "good luck in korea");
  }

  @Test
  public void test_socialFoursquareCheckIn() throws IOException {
    checkPhrase("check me in at communitech", SOCIAL, SOCIAL_FOURSQUARE_CHECK_IN);
  }

  @Test
  public void test_socialFoursquareShowCheckins() throws IOException {
    checkPhrase("where have i checked in", SOCIAL, SOCIAL_FOURSQUARE_SHOW_CHECKINS);
  }

  @Test
  public void test_socialTwitterShowFollower() throws IOException {
    checkPhrase("show my twitter timeline", SOCIAL, SOCIAL_TWITTER_SHOW_FOLLOWER);
  }

  @Test
  public void test_socialTwitterShowTrending() throws IOException {
    checkPhrase("what is trending on twitter", SOCIAL, SOCIAL_TWITTER_SHOW_TRENDING);
  }

  @Test
  public void test_socialTwitterTweet() throws IOException {
    checkPhrase("tweet i want a burrito", SOCIAL, SOCIAL_TWITTER_TWEET, "message", "i want a burrito");
  }
  
  @Test
  public void test_sportsMisc() throws IOException {
    checkPhrase("what was the score of the game last night", SPORTS, SPORTS_MISC);
  }
  
  @Test
  public void test_applicationLaunch() throws IOException {
    checkPhrase("launch angry birds", APPLICATION, APPLICATION_LAUNCH, "appName", "angry birds");
  }
  
}
