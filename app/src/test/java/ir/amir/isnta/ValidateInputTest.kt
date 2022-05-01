package ir.amir.isnta

import ir.amir.isnta.util.ValidateInput
import org.junit.Assert.*
import org.junit.Test

class ValidateInputTest {

    val validateInput = ValidateInput()


    @Test
    fun `check phone number length`() {
        val lengthIsEleven = validateInput.validatePhoneNumber("09123456789")
        assertEquals(true, lengthIsEleven)

        val lengthIsTen = validateInput.validatePhoneNumber("0912345678")
        assertEquals(false, lengthIsTen)
    }

    @Test
    fun `check phone number characters just integer type`() {
        val phoneOne = validateInput.validatePhoneNumber("091234s6789")
        assertEquals(false, phoneOne)

        val phoneTwo = validateInput.validatePhoneNumber(" 9123456789")
        assertEquals(false, phoneTwo)

        val phoneThree = validateInput.validatePhoneNumber("09123456789")
        assertEquals(true, phoneThree)
    }

    @Test
    fun `check email validation`() {
        val emailOne = validateInput.validateEmail("skfasda.com")
        assertEquals(false, emailOne)

        val emailTwo = validateInput.validateEmail("@gmail.com")
        assertEquals(false, emailTwo)


        val emailThree = validateInput.validateEmail("skjdn.fjs@gmail.com")
        assertEquals(true, emailThree)

        val emailFour = validateInput.validateEmail("skjdnfjs326@yahoo.com")
        assertEquals(true, emailFour)
    }

    @Test
    fun `check username validation`() {
        val usernameOne = validateInput.validateUsername("3sdfs6")
        assertEquals(false, usernameOne)

        val usernameTwo = validateInput.validateUsername("%jspodjf")
        assertEquals(false,usernameTwo)


        val usernameThree = validateInput.validateUsername("ssdff626")
        assertEquals(true, usernameThree)

        val usernameFour = validateInput.validateUsername("skjd326_sd")
        assertEquals(true, usernameFour)
    }
}