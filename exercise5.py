# CODE for EXERCISE 5
# -------------------
# This exercise introduces
#   * Menus
#   * Dialogs
#
# The exercise aims are to introduce menus and dialogs

from tkinter import *

import tkinter.filedialog as filedialog
import tkinter.messagebox as messagebox

app = Tk()
app.title("GUI Example 5")
app.geometry('400x400')

current_filename = None
current_contents = None

def exitApp():
    app.destroy()

def giveHelp():
    ans = messagebox.askquestion("Not Much Help", "Are you sure you need help", \
                    default=messagebox.NO)
    print(ans)

def aboutMsg():
    messagebox.showinfo("About Exercise 5", "Exercise 5 covers menus and dialogs")

def openFile():
    global current_filename, current_contents

    filename = filedialog.askopenfilename( \
        title="Choose a file to open", \
        filetypes=[("Text","*.txt"), ("All", "*")] )
    print(filename)
    current_filename = filename
    with open(current_filename, "r") as f:
        current_contents = f.read()

def saveFile():
    global current_filename, current_contents

    with open(current_filename, "w") as f:
        f.write(current_contents)

def convertFile():
    global current_contents

    if current_contents:
        current_contents = current_contents.upper()

# Create menu bar and menus
#-------------------
# The Menu widget is used twice
#   - for the menu bar
#   - for tne menu in the menu bar

menuBar = Menu(app)
app.winfo_toplevel()['menu'] = menuBar

file = Menu(menuBar)
file.add_command(label='Open', command=openFile)
file.add_command(label='Save', command=saveFile)
file.add_command(label='Quit', command=exitApp)
menuBar.add_cascade(label="File", menu=file)

edit = Menu(menuBar)
edit.add_command(label='Convert to upper', command=convertFile)
menuBar.add_cascade(label="Edit", menu=edit)

hlp = Menu(menuBar)
hlp.add_command(label='Help', command=giveHelp)
hlp.add_command(label='About', command=aboutMsg)
menuBar.add_cascade(label="Help", menu=hlp)

app.mainloop()
