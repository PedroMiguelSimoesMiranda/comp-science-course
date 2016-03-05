#! /bin/sh
cd src
java -classpath . security.Keygen CA
java -classpath . security.Keygen user1
java -classpath . security.Keygen user2
java -classpath . security.Keygen user3

BASEDIR=$(dirname $0)

java -classpath . security.CertificateGen $BASEDIR/Keys/CA/Private.prk user1 $BASEDIR/Keys/user1/Public.pbk
java -classpath . security.CertificateGen $BASEDIR/Keys/CA/Private.prk user2 $BASEDIR/Keys/user2/Public.pbk
java -classpath . security.CertificateGen $BASEDIR/Keys/CA/Private.prk user3 $BASEDIR/Keys/user3/Public.pbk
