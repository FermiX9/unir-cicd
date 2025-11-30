# Dockerfile for creating Jenkins agent with Docker capabilities
FROM jenkins/agent:jdk17

USER root

ENV JENKINS_MASTER_URL="http://192.168.1.45:8080/"
ENV JENKINS_MASTER_PORT=8080
ENV JENKINS_AGENT_NAME=node01-docker
ENV JENKINS_SECRET=508929f68c7d227d3f61686ce94d612eca37f5384410eaa82b37b881dd50922e
ENV WORKDIR=/workspace

# Crear el directorio de trabajo y dar permisos completos
RUN mkdir -p /workspace && chmod 777 /workspace

WORKDIR /workspace

# EntryPoint que ejecuta el agente tal cual tu línea docker run
ENTRYPOINT ["sh", "-c", "curl -sO ${JENKINS_MASTER_URL}/jnlpJars/agent.jar -o /workspace/agent.jar && java -jar /workspace/agent.jar -url ${JENKINS_MASTER_URL} -secret ${JENKINS_SECRET} -name ${JENKINS_AGENT_NAME} -workDir ${WORKDIR} -webSocket"]