package com.example.demo.exception

class UserNotFoundException(
    userId: Long,
): GeneralBadRequestException(
    message = "User not found with id: $userId"
)
