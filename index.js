const express = require('express')
const app = express()

app.get('/', function (req, res) {
    res.send(JSON.stringify(req.headers))
});

app.listen(3000, function () {
    console.log('Header printer app listening on port 3000!')
})
