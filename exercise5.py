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
current_file_changed = False

def exitApp():
    if not confirmUnsavedChanges("quit"):
        return

    app.destroy()

def giveHelp():
    ans = messagebox.askquestion("Not Much Help",
                                 "Are you sure you need help",
                                 default=messagebox.NO)
    print(ans)

def aboutMsg():
    messagebox.showinfo("About Exercise 5",
                        "Exercise 5 covers menus and dialogs")

def openFile():
    global current_filename, current_contents, current_file_changed

    if not confirmUnsavedChanges("open file"):
        return

    filename = filedialog.askopenfilename(
        title="Choose a file to open",
        filetypes=[("Text","*.txt"), ("All", "*")])
    print(filename)

    if filename:
        with open(filename, "r") as f:
            current_contents = f.read()
            current_file_changed = False
            current_filename = filename

def saveFile():
    global current_filename, current_contents, current_file_changed

    if not current_filename:
        messagebox.showerror("Error", "No open file!")
        return

    if not current_file_changed:
        messagebox.showinfo("Changes", "No changes to be saved.")
        return

    with open(current_filename, "w") as f:
        f.write(current_contents)
        current_file_changed = False

def convertFile():
    global current_filename, current_contents, current_file_changed

    if not current_filename:
        messagebox.showerror("Error", "No open file!")
        return

    if current_contents:
        current_contents = current_contents.upper()
        current_file_changed = True

def confirmUnsavedChanges(action):
    global current_file_changed

    if not current_file_changed:
        return True

    ans = messagebox.askquestion(
        "Unsaved changes",
         "Do you want to {}?".format(action),
         default=messagebox.NO)

    return ans == "yes"


# Create menu bar and menus
# -------------------------

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
