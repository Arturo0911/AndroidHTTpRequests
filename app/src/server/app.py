from flask import Flask

app = Flask(__name__)

@app.route("/",methods=['POST'])
def index():
    return "this is the method post"

app.run(host = "0.0.0.0", port = 5000, debug=True)
