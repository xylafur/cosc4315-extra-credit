CC=kotlinc
FILES=main.kt
CC_FLAGS=-include-runtime -d
OUT=prog.jar

compile:
	$(CC) $(FILES) $(CC_FLAGS) $(OUT)

run: compile
	java -jar $(OUT) input.txt

clean:
	rm $(OUT)
