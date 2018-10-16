# NGINX image with build artifact baked

FROM nginx:1.10.0

MAINTAINER Andrew Parise
ENV REFRESHED_AT 2018-10-16

# Remove default NGINX config, so it can be replaced by our custom version
RUN rm /etc/nginx/conf.d/default.conf
ADD ./conf/default.conf /etc/nginx/nginx.conf
ADD ./conf/cors_support /etc/nginx/cors_support

RUN touch /var/run/nginx.pid && \
  chown -R www-data:www-data /var/run/nginx.pid && \
  chown -R www-data:www-data /var/cache/nginx

USER www-data

VOLUME /var/www

EXPOSE 8087
