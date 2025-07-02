package empire.digiprem.utils

import kotlinx.datetime.LocalDate
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import kotlin.random.Random

import java.time.LocalDate as JavaLocalDate

object tools {
    fun createAvatarImage(firstLetter: String, size: Int = 160): ByteArray {
        val letters=firstLetter.substring(0,2)
        val image = BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
        val g: Graphics2D = image.createGraphics()

        // Fond coloré (exemple : bleu clair)
        g.color =randomColor()
        g.fillRect(0, 0, size, size)

        // Paramètres du texte (lettre)
        g.color = Color.WHITE
        val fontSize = (size * 0.6).toInt()
        g.font = Font("Arial", Font.BOLD, fontSize)

        // Centrer la lettre
        val fm: FontMetrics = g.fontMetrics
        val x = (size - fm.charWidth(letters.first())) / 2
        val y = ((size - fm.height) / 2) + fm.ascent

        g.drawString(letters.toString(), x, y)
        g.dispose()

        // Convertir l'image en tableau de bytes PNG
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }
    fun randomColor(): Color {
        val red = Random.nextInt(0, 100)
        val green = Random.nextInt(0, 100)
        val blue = Random.nextInt(0, 100)
        return Color(red, green, blue)
    }
}

fun LocalDate.toJavaLocalDate(): JavaLocalDate {
    return JavaLocalDate.of(this.year, this.monthNumber, this.dayOfMonth)
}
fun JavaLocalDate.toKtxLocalDate(): LocalDate {
    return LocalDate(this.year, this.monthValue, this.dayOfMonth)
}
