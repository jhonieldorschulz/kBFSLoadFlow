# kBFSLoadFlow

## Overview
kBFSLoadFlow is a BFS Load Flow service developed in Kotlin using the Spring Boot framework. This service is designed to perform load flow analysis using the Breadth-First Search (BFS) algorithm.

## Features
- **Load Flow Analysis**: Perform efficient load flow analysis for electrical networks.
- **Kotlin/Spring Boot**: Utilizes Kotlin for a concise and expressive codebase, and Spring Boot for robust and scalable backend services.

## Requirements
- JDK 11 or higher
- Maven 3.6.3 or higher

## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/jhonieldorschulz/kBFSLoadFlow.git
    ```
2. Navigate to the project directory:
    ```sh
    cd kBFSLoadFlow
    ```
3. Build the project:
    ```sh
    ./mvnw clean install
    ```

## Running the Application
To run the application locally, use the following command:
```sh
./mvnw spring-boot:run
```

## Usage
Once the application is running, you can access the load flow service via the provided API endpoints. Detailed API documentation will be available through the application's Swagger UI.

## Project Structure
- `src/main/kotlin`: Contains the Kotlin source files.
- `src/main/resources`: Contains application configuration files.
- `pom.xml`: Maven configuration file.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For any inquiries or support, please contact the repository owner through GitHub.

## Acknowledgments
Special thanks to all contributors and the open-source community for their valuable input and support.
