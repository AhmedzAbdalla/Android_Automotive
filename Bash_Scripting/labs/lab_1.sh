#!/bin/bash


declare -a myArray
myArray=(Mansoura Rome Giza Alex Gaza)

#echo ${#myArray[@]}    # print length of array
#echo ${myArray[@]}    # @ is replaced with non empty indicies
#echo "${myArray[@]}"  # print indviduals elements with double quoted "index0" "index0" "index0"
#echo "${myArray[*]}"  # print all elements with double quoted  "index0 index0 index0"


for item in "${myArray[@]}"; do
    echo "${item}"
done

for item in "${myArray[*]}"; do
    echo "${item}"
done

declare -A assoc_array=([key1]="value1" [key2]="value2")

echo "${assoc_array[key1]}" 
#echo ${a_arr["fruit"]}
#sed -n '3,5p' ./tt
#sed -n 'p' ./tt