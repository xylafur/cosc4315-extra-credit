CC=kotlinc
FILES=hello.kt
CC_FLAGS=-include-runtime -d
OUT=hello.jar

run: compile
	java -jar $(OUT)

compile:
	$(CC) $(FILES) $(CC_FLAGS) $(OUT)

clean:
	rm $(OUT)
