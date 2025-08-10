# Use OpenJDK 17 as base
FROM maven:3.9.6-eclipse-temurin-17

# Install Playwright dependencies (for Chromium, Firefox, WebKit)
RUN apt-get update && apt-get install -y \
    wget \
    libnss3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libcups2 \
    libdrm2 \
    libxkbcommon0 \
    libxcomposite1 \
    libxrandr2 \
    libxdamage1 \
    libgbm1 \
    libasound2 \
    libpangocairo-1.0-0 \
    libpango-1.0-0 \
    libcairo2 \
    libx11-xcb1 \
    libxtst6 \
    libxss1 \
    fonts-liberation \
    libappindicator3-1 \
    libatk-adaptor \
    xvfb \
    && apt-get clean

# Set work directory
WORKDIR /app

# Copy Maven project
COPY pom.xml .
COPY src ./src
COPY testng.xml .

# Install browsers for Playwright
RUN mvn dependency:resolve
RUN mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"

# Run tests (with virtual display for non-headless)
CMD xvfb-run mvn clean test
