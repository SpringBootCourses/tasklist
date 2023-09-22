<#ftl encoding="UTF-8">
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>You have a task to do in 1 hour</title>
    <style>
        #body {
            display: flex;
            flex-direction: column;
            font-family: "Segoe UI", serif;
            align-items: center;
            background-color: #607FF2;
            font-size: 20px;
            height: 100%;
        }

        #div {
            display: block;
            flex-direction: column;
            margin: 10%;
            font-family: "Segoe UI", serif;
            background-color: white;
            padding: 3%;
            border-radius: 20px;
            color: black;
        }

        #button {
            background-color: #607FF2;
            border-radius: 10px;
            padding: 5px;
            color: white;
            text-decoration: none;
        }

        #a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="body">
    <div id="div">
        <h1 style="text-align: center">Reminder</h1>
        <p>Hello, ${name}!</p>
        <p>This is friendly reminder that you have a task to do in 1 hour.</p>
        <div>
            <hr>
            <p>Title: ${title}</p>
            <#if description??>
                <p>Description: ${description}</p>
            </#if>
            <hr>
        </div>
        <p>You can get more information about this task in the app.</p>
        <p>With best regards, <br> Ilya Lisov</p>
        <a id="a" href="https://github.com/IlyaLisov/tasklist">Github</a>
    </div>
</div>
</body>
</html>