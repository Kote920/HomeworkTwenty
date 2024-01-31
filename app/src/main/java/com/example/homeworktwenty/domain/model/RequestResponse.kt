package com.example.homeworktwenty.domain.model

data class RequestResponse(var result: Result)


enum class Result(){
    SUCCESSFUL,
    FAILED
}
