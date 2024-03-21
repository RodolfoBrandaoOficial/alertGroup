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

- You can also test the webhook using `curl`. Here's an example command:
```bash
curl --location --request POST 'http://127.0.0.1:3377/webhook?alert=sim&mensagem=Jo%C3%A3o&tiposervico=Instala%C3%A7%C3%A3o'
--header 'Authorization: Basic QWRtaW46U2VuaGFAMTIzMTIz'
```

### Project Purpose

This project is designed to enable a support room to receive notifications of new installation or adjustment requests from external technicians directly on a TV with a system compatible with the Java language, such as a mini-computer.
## Contributing

Contributions are welcome! Feel free to open an issue or send a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

3 / 3





