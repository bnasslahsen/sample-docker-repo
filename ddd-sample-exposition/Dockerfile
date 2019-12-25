FROM adoptopenjdk/openjdk8-openj9

VOLUME /tmp
RUN addgroup -S demo && adduser -S demo -G demo

ARG DEPENDENCY=dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#RUN chmod -R +r /app

EXPOSE 8080
# Run as non-root
#USER demo
# Lancement de l'application java avec le paramètre -Djava.security.egd pour
# un démarrage plus rapide du tomcat embarqué cf. https://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
USER demo

#ENTRYPOINT ["ping","www.google.fr"]
ENTRYPOINT ["java","-noverify","-Xscmx50M","-Xshareclasses","-Xquickstart","-Djava.security.egd=file:/dev/./urandom", "-Dspring.main.lazy-initialization=true", "-XX:TieredStopAtLevel=1","-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap" , "-XX:MaxRAMFraction=1" , "-XshowSettings:vm" , "-cp","app:app/lib/*","fr.training.spring.shop.SpringBootApp"]
