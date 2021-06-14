#! /bin/bash

if  [ ! -d $1 ];then
   echo "creating directory: $1"
   mkdir $1
fi

for file in `ls *.jtl`
do
   if [ -f $file ];then
	dir=${file%.*}
	echo "processing: $dir"
	jmeter -g $file -o $1/$dir
	cp $file $1/$dir/
   fi
done

