FROM maven
ARG FOLDER_NAME
COPY  $FOLDER_NAME /usr/$FOLDER_NAME
RUN cd /usr/$FOLDER_NAME
CMD ["pwd"]