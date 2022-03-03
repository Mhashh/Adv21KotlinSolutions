import java.io.File

fun subsetOfThisString(str : String,str2 : String) : Boolean{
    str.forEach {
        if(it !in str2){
            return false
        }
    }

    return true
}

fun main(args : Array<String>){
    val file = File("E:\\IdeaProjects\\Adv\\Input\\day8.txt")
    val fourdigitout = file.readLines().map { it.split(" | ")[1]}

    var count = 0
    fourdigitout.forEach {

        var tmp=it.split(' ')

        tmp.forEach { eachdigit:String ->
            if(eachdigit.length in arrayOf(2,3,4,7)){
                count++
            }
        }
    }
    //no of 1,4, 7 ,8 each of them have unique length so just compare with
    // the string lengths
    println(count)


    //part2 --------------------------------
    //extracting 10 signal combinations for each digit
    //no of 1,4, 7 ,8 each of them have unique length so just compare with
    // the string lengths
    // for (length 6)-> 9,0,6
    //      4 is a subset of 9 only
    //      then 7 is a subset of 0
    //      remaining must be 6
    //for (length 5)->3,5
    //      3 is a subset of 1
    //      remaining must be 5 or 2 special Handling required
    val tensignals = file.readLines().map { it.split(" | ")[0]}

    val digits = Array<String>(10) { "" }
    var output : Int = 0
    var sumOfOutput : Long = 0


    tensignals.forEachIndexed {index:Int,it:String->
        val tmp=it.split(' ')

        //logic for signal interpretation for each digit
        tmp.forEach { signal:String->
            when(signal.length) {
                2->digits[1]=signal
                3->digits[7]=signal
                4->digits[4]=signal
                7->digits[8]=signal
            }
        }

        //this just stores intersection of
        //signal for 1 and 4 , which is a subset of 5 only
        digits[5] = digits[4].filter {it !in digits[1] }

        //4 digit signal to decode
        var place = 1000
        output = 0

        fourdigitout[index].split(' ').forEach { s:String->
            var digitmatch = 0
            when(s.length){
                2->digitmatch = 1
                3->digitmatch = 7
                4->digitmatch = 4
                5->{
                    if(subsetOfThisString(digits[1],s)){
                        digitmatch = 3
                    }
                    else if(subsetOfThisString(digits[5],s)){
                        digitmatch = 5
                    }else{
                        digitmatch = 2
                    }
                }

                6->{
                    var i = 0
                    if(subsetOfThisString(digits[4],s)){
                        digitmatch = 9
                    }else if(subsetOfThisString(digits[7],s)){
                        digitmatch = 0
                    }else{
                        digitmatch = 6
                    }
                }

                7->digitmatch = 8
            }
            output+=(digitmatch*place)
            place/=10

        }
        sumOfOutput+=output


    }

    println("Sum of output : $sumOfOutput")
}

