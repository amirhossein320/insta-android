package ir.amir.isnta.util

class ValidateInput {

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        val pattern = Regex("(?<=\\s|^)\\d+(?=\\s|\$)")
        return if (phoneNumber.length == 11) {
             pattern.matches(phoneNumber)
        } else false
    }

    fun validateEmail(email: String): Boolean {
        val pattern = Regex("^([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)\$")
        return pattern.matches(email)
    }

    fun validateUsername(username: String): Boolean {
       return !username.startsWith(".") &&
                !username.startsWith("_") &&
                !username.endsWith(".") &&
                !username.endsWith("_") &&
                !username.contains("..") &&
                !username.contains("__") &&
                !username.contains("._") &&
                !username.contains("_.") &&
                username.length >= 5
    }

    fun validatePassword(password: String) = password.length >= 6
}
