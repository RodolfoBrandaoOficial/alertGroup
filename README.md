

# alertGroup

# 🚨 Automation of Alerts for WhatsApp or Telegram Groups 📲

This project consists of a Java application to automate the sending of alerts to WhatsApp or Telegram groups. The application uses SparkJava to create an HTTP server that receives webhook requests and sends the alerts to the groups.

## Features

- Sending alerts to WhatsApp or Telegram groups.
- Integration with instant messaging services via webhooks.
- Basic authentication to ensure the security of requests.

## How to Use

### Prerequisites

- Java JDK installed on the machine.
- Java IDE (such as Eclipse, IntelliJ IDEA) for development.
- Account on instant messaging services (WhatsApp or Telegram) to set up the integration.

### Configuration

1. Clone this repository to your local environment:

git clone https://github.com/RodolfoBrandaoOficial/alertGroup.git




2. Open the project in your Java IDE.

3. Configure the basic authentication credentials in the `App.java` file, in the `USERNAME` and `PASSWORD` variables.

4. Run the project from the `App.java` class.

5. After running the project, the HTTP server will be running on port 3377.

6. Configure a webhook pointing to `http://localhost:3377/webhook` in the instant messaging services (WhatsApp or Telegram).

### Usage

- Upon receiving a request on the webhook, the server will process the alert and send it to the configured groups.

## Contributing

Contributions are welcome! Feel free to open an issue or send a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
