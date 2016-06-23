val x = null
val y: String = x

//val z: Int = x //Not possible

if (true) 1 else false //Neither boolean nor int -> AnyVal

def error(msg: String) = throw new Error(msg)

error("test")
