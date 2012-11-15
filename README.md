napi-java
===========

Maluuba nAPI is a new API that allows developers to add Natural Language Understanding (NLU)
to their software.

Features
--------

This API currently supports 22 different domains and around 70 different intents or actions.
We also parse out numerous entities. We believe that this API is the beginning of something great,
something that is going to completely change how people interact with their devices. But it is
just a beginning. We are starting out with the given domains, but plan on massively expanding it as
feature requests come in, and we see how people want to use this technology.

Access
------

Please sign up at the [Maluuba Developer Site](http://developer.maluuba.com) and apply for access.
We are currently in an alpha stage right now, and giving out API keys to interested third parties.
Once you have been approved, you will receive an API key that you can use with this client.

Installation
------------

The JAR is not currently available on any central Maven repositories, but you can easily clone
this repository and install it to your local Maven just by typing:

```
mvn compile install
```

Before installing, make sure to edit `src/main/resources/MaluubaCredentials.properties` to provide your API key:

```
$ cat src/main/resources/MaluubaCredentials.properties
# You can find your API key at developer.maluuba.com
API_KEY=your-api-key-here
```

It can then be imported into any compatible Java project by including the following dependency in your
`pom.xml`:

```xml
  	<dependency>
  		<groupId>com.maluuba</groupId>
  		<artifactId>napi-java</artifactId>
  		<version>0.0.1-SNAPSHOT</version>
  	</dependency>
```

Javadocs
--------

Complete Javadocs for the client library can be found [here](http://maluuba-napi-java.s3-website-us-east-1.amazonaws.com/).

Usage
-----

Here's a simple example using the Maluuba nAPI to categorize and extract information about a
naturally-spoken sentence:

```java
    Properties p = new Properties();
    p.load(this.getClass().getResourceAsStream("/MaluubaCredentials.properties"));
    client = new MaluubaNAPIClient(p.getProperty("API_KEY"));

    InterpretResponse response = client.interpret(new InterpretRequest("Set up a meeting with Bob tomorrow night at 7 PM to discuss the TPS reports"));

    Assert.assertEquals(NAPICategory.CALENDAR, response.getCategory());
    Assert.assertEquals(NAPIAction.CALENDAR_CREATE_EVENT, response.getAction());
    System.out.println(response.getEntities());

    /*
     * {dateRange=[{"start":1352955600000,"end":1353042000000}], title=[meeting to discuss the tps reports], timeRange=[{"start":18000000,"end":18000000}], contacts=[{name=bob}]}
     */
```

Contact
-------

This API is still an early work in progress, so we greatly appreciate all the feedback we receive,
whether it's about bugs, features, or use cases. Here are some of the ways you can bring things
to our attention:

  * Send an e-mail to our mailing list at [napi@maluuba.com](mailto:napi@maluuba.com)
  * File an issue against our GitHub issue tracker (above)
  * Post on our [Support Forums](http://developer.maluuba.com/forum)
  * Join our [IRC channel (#maluuba on FreeNode)](irc://freenode.net/#maluuba) and talk to our developers in real-time
  * Follow us on Twitter [@MaluubaInc](http://twitter.com/maluubainc) and receive the latest information and updates