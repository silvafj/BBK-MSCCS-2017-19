# CODE for EXERCISE 5
# -------------------
# This exercise introduces  
#   * Menus
#   * Dialogs
#
# The exercise aims are to introduce menus and dialogs

from tkinter import *

app = Tk()
app.title("GUI Example 5")
app.geometry('400x400')

# Create handlers for menu items
# ------------------------------
# These function handle the menu selections
# 
# Three of the handlers use dialogs
#   - a dialog is a special window that forces a response
#   - some dialogs are for messages / questions / errors
#   - file choosing is done using a dialog

def exitApp():
    app.destroy()

def giveHelp():
    ans = messagebox.askquestion("Not Much Help", "Are you sure you need help", \
                    default=messagebox.NO)
    print(ans)

def aboutMsg():
    messagebox.showinfo("About Exercise 5", "Exercise 5 covers menus and dialogs")

def openFile():
    filename = filedialog.askopenfilename( \
        title="Choose a file to open", \
        filetypes=[("Text","*.txt"), ("All", "*")] )
    print(filename)
    
# Create menu bar and menus
#-------------------
# The Menu widget is used twice
#   - for the menu bar
#   - for tne menu in the menu bar

menuBar = Menu(app)
app.winfo_toplevel()['menu'] = menuBar

file = Menu(menuBar)
file.add_command(label='Open', command=openFile)
file.add_command(label='Quit', command=exitApp)
menuBar.add_cascade(label="File", menu=file)

hlp = Menu(menuBar)
hlp.add_command(label='Help', command=giveHelp)
hlp.add_command(label='About', command=aboutMsg)
menuBar.add_cascade(label="Help", menu=hlp)

app.mainloop()

