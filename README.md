# The world of blind virologists
## AKA A világtalan virológusok világa

Group assignment at uni to create a basic game in JAVA
## Contributors
- [Dániel Száraz](https://github.com/DannySS123)
- [Sámuel Fekete](https://github.com/Tschonti)
- [Ádám Domonkos](https://github.com/domonkosadam)
- [Jónás Jurásek](https://github.com/J0NAS12)
- [Zsombor Kászonyi](https://github.com/Moonlander1)

## Compile
Compiling requires JDK 1.8!
Run `compiled/compile.bat`. Note that this requires the JDK's bin folder to be set in the Path variable. The commands in this file generate the class files in the `compiled` directory and a `.jar` file in the root directory.

## Test
There are various test cases described in the documentation. Each of them have a `.bat` file in the `test` directory, which start the `prototype.jar` file, read input from a text file, write to another, and then compare the output with the expected ouput of the testcase. The final version doesn't support testing, so if you want to try it out, you'll have to revert to the Prototype tag/release and compile that version to generate `prototype.jar`.

## How to use Prettier
- Install all npm packeges with \
`npm i`
- If you have installed the package locally \
`npx prettier --write "**/*.java"`
- Or globally \
`prettier --write "**/*.java"`
