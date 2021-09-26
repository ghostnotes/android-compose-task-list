package co.ghostnotes.sample.compose.tasklist.domain.usecase

interface UseCase<INPUT, OUTPUT> {
    operator fun invoke(input: INPUT): OUTPUT
}