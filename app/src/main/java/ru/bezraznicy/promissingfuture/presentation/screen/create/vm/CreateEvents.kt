package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

sealed class CreateEvent {
    class SaveModel(
        val name: String,
        val description: String,
        val time: String
    ): CreateEvent()
}
