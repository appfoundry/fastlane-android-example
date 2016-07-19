package be.vergauwen.simon.androidretaindata.core.model

data class GithubRepo(val name: String, val html_url: String, val description : String)

fun List<GithubRepo>.toStringList() : List<String> {
    val strings = mutableListOf<String>()
    this.forEach { strings.add(it.name) }
    return strings
}