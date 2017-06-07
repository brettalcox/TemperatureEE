# TemperatureEE
EJB and REST API to retrieve and persist temperature intervals coming from ActiveMQ queue. Mitochondria is a daemon JAR that pulls back Nest and Wunderground data every 15 minutes, sends a JMS message which is then picked up by a message driven bean in the EJB.
