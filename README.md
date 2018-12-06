# cosc4315-extra-credit

name                |   Unix ID     |   PSID
James Richardson    |   cosc2994    |   1555520


Language Used: Kotlin

Obtaining the kotlin compiler
    If the kotlin compiler is not installed, I have provided a script that will
    install it using curl/sdkman.  I was able to run it without any super user
    permissions.  It puts the kotlin compiler in my local home directory.

    To install it with my script just run
        bash install.sh

    If you don't want to use my install script and install the kotlin compiler


Building:
    if kotlinc is installed on the system or you used my install script then
    simply run
        `make`

    if it is not installed and you do not trust my install script (read it
    before you decide this, it is literally the install instructions directly
    from the kotlin website) then you can call the compiler directly (I will
    make sure that you have permissions)
        `/home/y2018/fall/cs4315/2/cosc2994/.sdkman/candidates/kotlin/current/bin/kotlinc main.kt -include-runtime -d prog.jar`

Running:
    Run the compiled jar with
        java -jar <file>

How I solved this:
    i used regex and string substitution along with casting

    I check for either add(<number>, <number>) or multiply(<number>, <number>)
    and then I replace that line with whatever it equals.  I keep doing this
    until there is only a number left.  If I don't see the add function,
    multiply function or just a number then I know that the line is invalid and
    I output that instead of the result.

    For example:
        "multiply(1, add(4, 4))"
        "multiply(1, 8)"
        "8"

Why this is functional:
    as you can see by looking at my source code, I have no assignments.  All of
    my functions do a single thing and return a single result, there are no
    side effects.  There is no iteration, the main function is called
    recursively with the result of the previous run until we hit an error or
    there is only the answer left.
