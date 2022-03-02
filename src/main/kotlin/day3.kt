
import java.io.File

fun main(args: Array<String>) {
    val file : File = File("E:\\IdeaProjects\\Adv\\Input\\advday3.txt")
    var gammainput : MutableList<String> = file.readLines().toMutableList()


    //PART ONE GAMMA RATE AND EPSILON RATE
    var gammabits : IntArray = IntArray(gammainput[0].length)//zero initialization
    var epsilonbits : IntArray = IntArray(gammabits.size)//zero initialization

    //INCREMENTING GAMMABITS , IF NEGATIVE -> MORE 1'S ELSE IF POSITIVE -> MORE 0'S
    gammainput.forEach {
        for(i in 0..it.length-1){
            if(it[i]=='0')
                gammabits[i]++;
            else
                gammabits[i]--;
        }
    }

    //ACTUALLY PUTTING 0 AND 1S
    //ALSO FINDING EPSILON BITS
    for(i in 0..(gammabits.size-1)){
        if(gammabits[i]>0) {
            gammabits[i] = 0;
            epsilonbits[i] = 1
        }
        else{
            gammabits[i]=1;
            epsilonbits[i] = 0
        }
    }
    gammabits.forEach { print(it) }
    println()
    epsilonbits.forEach { print(it) }
    println()
    var gammarate : Int = binaryToDecimal(gammabits)
    var epsilonrate : Int = binaryToDecimal(epsilonbits)


    println("Gamma Rate : ${gammarate} Epsilon Rate : ${epsilonrate} Power consumption : ${gammarate*epsilonrate}")

    //PART TWO OXYGEN GENERATOR RATING AND C02 SCRUBBER RATING

    //OXYGEN



    var oxygenbits : IntArray = IntArray(gammainput[0].length)

    var bitpos = 0;

    while(gammainput.size > 1){

        gammainput.forEach {
            if(it[bitpos] == '0') {
                oxygenbits[bitpos]++;
            }
            else{
                oxygenbits[bitpos]--;
            }
        }
        //more 1's in this bitpos
        if(oxygenbits[bitpos]<=0){
            var i = 0
            while(i < (gammainput.size)){
                if(gammainput[i][bitpos]=='0') {
                    gammainput.removeAt(i)
                    i--;
                }
                i++;
            }
        }

        else{
            var i = 0
            while(i < (gammainput.size)){
                if(gammainput[i][bitpos]=='1') {
                    gammainput.removeAt(i)
                    i--;
                }
                i++;
            }
        }

        bitpos = (bitpos+1)%(gammainput[0].length)

    }

    println(gammainput[0])

    var o2rating : Int = binaryToDecimal(gammainput[0])
    //OXYGEN
    //CO2

    

    var co2input : MutableList<String> = file.readLines().toMutableList()

    var co2bits : IntArray = IntArray(co2input[0].length)
    bitpos = 0
    while(co2input.size > 1){

        co2input.forEach {
            if(it[bitpos] == '0') {
                co2bits[bitpos]++;
            }
            else{
                co2bits[bitpos]--;
            }
        }
        //more 1's in this bitpos
        if(co2bits[bitpos]<=0){
            var i = 0
            while(i < (co2input.size)){
                if(co2input[i][bitpos]=='1') {
                    co2input.removeAt(i)
                    i--;
                }
                i++;
            }
        }

        else{
            var i = 0
            while(i < (co2input.size)){
                if(co2input[i][bitpos]=='0') {
                    co2input.removeAt(i)
                    i--;
                }
                i++;
            }
        }

        bitpos = (bitpos+1)%(co2input[0].length)

    }
    println(co2input[0])
    var co2rating : Int = binaryToDecimal(co2input[0])

    println("Oxygen generator rating ${o2rating} CO2 scrubber rating ${co2rating} life support rating ${o2rating*co2rating}")
    //CO2
}

fun binaryToDecimal(inp : String) : Int{
    var x : Int = 0
    var twoPow : Int = 1;

    for(i in (inp.length-1)downTo 0){

        x = x+(if(inp[i]=='0') 0 else twoPow)
        twoPow = twoPow*2
    }


    return x
}

fun binaryToDecimal(inp : IntArray) : Int{
    var x : Int = 0
    var twoPow : Int = 1;
    for(i in (inp.size-1)downTo 0){

        x = x + (inp[i]*twoPow)
        twoPow = twoPow*2
    }


    return x
}