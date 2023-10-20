# Password Manager Application

A simple password manager application in Java that allows users to generate and store passwords for various websites securely.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Security](#security)
- [Contributing](#contributing)

## Features
This password manager application offers the following features:

1. **Generate Passwords:** Users can generate strong passwords of a specified length.
2. **Store Passwords:** Passwords are securely stored in an SQLite database with website labels.
3. **Retrieve Passwords:** Users can retrieve stored passwords for specific websites.
4. **Change Passwords:** Passwords for existing websites can be updated.
5. **Security Code:** A security code (4-digit number) is used to prevent unauthorized access.

## Prerequisites
Before using this application, ensure you have the following installed:

- Java Development Kit (JDK)
- SQLite database support
- A code editor or integrated development environment (IDE)

## Installation
1. Clone or download this repository to your local machine.

   ```bash
   git clone https://github.com/yourusername/password-manager.git

## Usage
To use the password manager application, follow these steps:

### Set Your Security Code

- When you run the application for the first time, you will be prompted to set a 4-digit security code. This code is required for certain operations, such as changing stored passwords.

### Main Menu

- After setting the security code, the main menu will be displayed with the following options:
    1. Generate new password and save
    2. View saved password
    3. Change password for saved websites
    4. Exit

### Generate a New Password

- To generate a new password for a specific website, select option 1 and enter the website name and password length.

### View Saved Password

- To view a saved password, select option 2, enter the website name, and the application will display the stored password.

### Change Password

- If you want to change a password for a saved website, select option 3 and enter your security code, website name, and the new password.

### Exit

- Select option 4 to exit the application.

## Security
This application uses encryption to protect stored passwords. The `EncryptDecrypt` class handles password encryption and decryption using AES encryption with a PBKDF2 key derivation function.

### Security Recommendations
- It's essential to keep your security code confidential and secure.
- Ensure that the encryption password (`PASSWORD`) in the `EncryptDecrypt` class is strong and known only to authorized users.
- Protect your SQLite database (`sqlite.db`) from unauthorized access.

## Contributing
Contributions are welcome! If you'd like to contribute to this project, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes with clear and concise messages.
4. Create a pull request and provide a detailed description of your changes.


