import java.io.File

fun main(args: Array<String>) {
    val file: File = File("E:\\IdeaProjects\\Adv\\Input\\advday4.txt")
    //file input in a list<string>
    var day4input: List<String> = file.readLines()

    //converting list<String> to Array of Mutable list of int
    var bingoInput:MutableList<MutableList<Int>> = MutableList<MutableList<Int>>(1,{ it->day4input[0].split(',').map {it.toInt() }.toMutableList()})

    var inpi = 2
    //bingo matices to int
    //1..5,6..10,11..15,...............,595..600
    while(inpi<day4input.size) {

        bingoInput.add(day4input[inpi].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput.add(day4input[inpi+1].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput.add(day4input[inpi+2].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput.add(day4input[inpi+3].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput.add(day4input[inpi+4].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        inpi+=6
    }

    var bingoNums = 0;

    var bingoMats = 1;


    found@while(bingoNums<bingoInput[0].size){
        bingoMats=1
        while(bingoMats<bingoInput.size) {

            var keyi = -1;
            var keyj = -1;
            var sumh = 0;
            var sumv = 0;
            //finding bingo input in matrix
            rowlabel@ for(r in bingoMats..bingoMats+4){
                for(c in 0..4){
                    if(bingoInput[r][c] == bingoInput[0][bingoNums]){

                        keyi = r
                        keyj = c
                        bingoInput[r][c] += 100
                        sumh = horizontalbingoCheck(bingoInput[r])
                        sumv = verticalbingoCheck(bingoMats,c,bingoInput)

                        break@rowlabel
                    }
                }
            }

            if(sumh>100 && sumv>100){
                println("Bingo ! in Matrix no ${bingoMats/5} or ${bingoMats} on Input ${bingoInput[0][bingoNums]} both horizontally and vertically")
                println("Required product ${unmarkedSum(bingoMats,bingoInput)*bingoInput[0][bingoNums]}")
                break@found
            }
            else if(sumh>100){
                println("Bingo ! in Matrix no ${bingoMats/5} or ${bingoMats} on Input ${bingoInput[0][bingoNums]} horizontally")
                println("Required product ${unmarkedSum(bingoMats,bingoInput)*bingoInput[0][bingoNums]}")
                break@found
            }
            else if(sumv>100){
                println("Bingo ! in Matrix no ${bingoMats/5} or ${bingoMats} on Input ${bingoInput[0][bingoNums]} vertically")
                println("Required product ${unmarkedSum(bingoMats,bingoInput)*bingoInput[0][bingoNums]}")
                break@found
            }
            bingoMats+=5
        }

        bingoNums++;

    }



    print("\nInput used : ")
    for(c in 0..bingoNums)
        print("${bingoInput[0][c]} ")
    println("\n")

    //part two
    var bingoInput2:MutableList<MutableList<Int>> = MutableList<MutableList<Int>>(1,{ it->day4input[0].split(',').map {it.toInt() }.toMutableList()})

    inpi = 2
    //bingo matices to int
    //1..5,6..10,11..15,...............,595..600
    while(inpi<day4input.size) {

        bingoInput2.add(day4input[inpi].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput2.add(day4input[inpi+1].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput2.add(day4input[inpi+2].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput2.add(day4input[inpi+3].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        bingoInput2.add(day4input[inpi+4].split("[ ]{1,2}".toRegex()).filterNot { it.isBlank() }.map { it.toInt() }.toMutableList())
        inpi+=6
    }
    bingoNums = 0;

    bingoMats = 1;

    outer@while((bingoInput2.size>=6) && (bingoNums<bingoInput2[0].size)){
        bingoMats=1

        while(bingoMats<bingoInput2.size) {

            var keyi = -1;
            var keyj = -1;
            var sumh = 0;
            var sumv = 0;
            //finding bingo input in matrix
            rowlabel2@for(r in bingoMats..bingoMats+4){
                for(c in 0..4){
                    if(bingoInput2[r][c] == bingoInput2[0][bingoNums]){

                        keyi = r
                        keyj = c
                        bingoInput2[r][c] += 100
                        sumh = horizontalbingoCheck(bingoInput2[r])
                        sumv = verticalbingoCheck(bingoMats,c,bingoInput2)
                        break@rowlabel2
                    }
                }
            }

            if(sumh>100 || sumv>100){

                if(bingoInput2.size==6){
                    println(
                        "Matrix : ${bingoMats} Last input : ${bingoInput2[0][bingoNums]} Required product ${
                            unmarkedSum(
                                bingoMats,
                                bingoInput2
                            ) * bingoInput2[0][bingoNums]
                        }"
                    )
                   //break@outer
                }
                for(c in 0..4)
                    bingoInput2.removeAt(bingoMats)

            }
            else {
                bingoMats+=5
            }
        }
        bingoNums++;

    }



}

fun horizontalbingoCheck(bingoMat : MutableList<Int>) : Int{
   var sum = 0;
    for(j in 0..4){
       if(bingoMat[j]>=100){
           sum += (bingoMat[j]%100)
       }
       else {
           sum = -1
           break
       }
    }




    return sum
}

fun verticalbingoCheck(firstrow:Int,j:Int,bingoMat : MutableList<MutableList<Int>>) : Int{
    var sum = 0;
    for(i in firstrow..firstrow+4){
        if(bingoMat[i][j]>=100){
            sum += (bingoMat[i][j]%100)
        }
        else {
            sum = -1
            break
        }
    }


    return sum
}

fun unmarkedSum(firstrow:Int,bingoMat : MutableList<MutableList<Int>>) : Int{

    var sum = 0

    for(i in firstrow..firstrow+4){
        for(j in 0..4){
            if(bingoMat[i][j]<100){
                sum += bingoMat[i][j]
            }
        }
    }

    return sum
}

