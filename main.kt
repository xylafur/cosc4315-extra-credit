import java.io.File
import java.io.InputStream

fun mulRegex(): Regex = """multiply\(\h*(\d+)\h*,\h*(\d+)\h*\)""".toRegex()
fun addRegex(): Regex = """add\(\h*(\d+)\h*,\h*(\d+)\h*\)""".toRegex()
fun numRegex(): Regex = """\A\d+$""".toRegex()

fun mulMatch(line: String): MatchResult? = mulRegex().find(line)
fun addMatch(line: String): MatchResult? = addRegex().find(line)
fun numMatch(line: String): MatchResult? = numRegex().find(line)

fun foundMatch(match: MatchResult?): Boolean = match != null

fun foundAdd(line: String): Boolean = foundMatch(addMatch(line))
fun foundMul(line: String): Boolean = foundMatch(mulMatch(line))
fun foundNum(line: String): Boolean = foundMatch(numMatch(line))


fun add(a: Int, b: Int): Int = a + b
fun addStrings(a: String, b: String): String = add(a.toInt(), b.toInt()).toString()

fun mul(a: Int, b: Int): Int = a * b
fun mulStrings(a: String, b:String): String = mul(a.toInt(), b.toInt()).toString()


fun replaceAdd(line: String, match: MatchResult, regex: Regex): String = line.replace(
        regex, addStrings(match.groups[1]!!.value, match.groups[2]!!.value))

fun replaceMul(line: String, match: MatchResult, regex: Regex): String = line.replace(
        regex, mulStrings(match.groups[1]!!.value, match.groups[2]!!.value))


fun doAddReplace(line: String): String = replaceAdd(line, addMatch(line)!!,
                                                    addRegex())
fun doMulReplace(line: String): String = replaceMul(line, mulMatch(line)!!,
                                                    mulRegex())

fun replaceNext(line: String): String = if(foundAdd(line)){
    doAddReplace(line) } else {
        if(foundMul(line)){ doMulReplace(line) } else {
            if(foundNum(line)){ line } else { "invalid" } } }

fun replaceAll(line: String): String = if (line == "invalid") {
    "Invalid Expression" } else { if(foundNum(line)) { line } else {
        replaceAll(replaceNext(line)) }}

fun main(args: Array<String>){
    if(args.size != 1){
        println("Supply input file as argument!")
        return
    }

    val inputStream: InputStream = File(args[0]).inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach {
        print(it)
        print(" = ")
        println(replaceAll(it))
    }}
}
