all: progs

progs:
	javac *.java

clean:
	rm *.class

serv:
	java -Djavax.net.ssl.keyStore=mySrvstore -Djavax.net.ssl.keyStorePassword=private.key Serveur

client1:
	java -Djavax.net.ssl.trustStore=mySrvstore -Djavax.net.ssl.trustStorePassword=private.key Client1

client2:
	java -Djavax.net.ssl.trustStore=mySrvstore -Djavax.net.ssl.trustStorePassword=private.key Client2

cert:
	rm private.key mySrvstore
	openssl genrsa -out private.key 4096
	keytool -genkey -keystore mySrvstore -keyalg RSA -storepass private.key
