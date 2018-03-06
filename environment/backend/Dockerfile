# Use an official Python runtime as a parent image
FROM nginx

# Set the working directory to /app
WORKDIR /usr/share/nginx/html

# Copy the current directory contents into the container at /app
ADD nginx.conf /etc/nginx
ADD index.html /usr/share/nginx/html

# Make port 80 available to the world outside this container
EXPOSE 80

# Define environment variable
ENV NAME tor14-backend
