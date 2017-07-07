import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Any.info(message: String) {
    val logger = LoggerFactory.getLogger(this.javaClass.name)
    logger.info(message)
}

fun Any.error(message: String, exception: Exception) {
    val logger = LoggerFactory.getLogger(this.javaClass.name)
    logger.error(message, exception)
}
