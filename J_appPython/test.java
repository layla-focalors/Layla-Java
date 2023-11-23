package J_appPython;

public class test {
    public class TextInv {
        app.python('import datetime')
        app.python('x = datetime.datetime.now()')
        app.python('print(x)')
        app.save('./main.py')
        app.python('for i in range(10):\n\tprint(i)')
    }
}
