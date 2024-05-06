db.createCollection("usuarios");
db.usuarios.insertOne({
    nombre: "John Doe",
    correo: "ejemplo@ejemplo.com",
    password: "ejemplo",
    telefono: "11122223344",
    fecha_nacimiento: new Date("2024-04-25T12:00:00Z"),
    sexo: "gato"
});

db.usuarios.insertOne({
    nombre: "Jane Doe",
    correo: "ejemplo@ejemplo.com",
    password: "ejemplo",
    telefono: "11122223344",
    fecha_nacimiento: new Date("2024-04-25T12:00:00Z"),
    sexo: "perro"
});

db.createCollection("mensajes");
db.mensajes.insertOne({
    emisor_id: ObjectId("6639348a533f06145df94942"),
    receptor_id: ObjectId("6639348a533f06145df94943"),
    contenido: "Hola =]",
    timestamp: new Date()
})

db.createCollection("chats");
db.chats.insertOne({
    miembros: [ObjectId("6639348a533f06145df94942"), ObjectId("6639348a533f06145df94943")],
    mensajes: [ObectId("663935eb533f06145df94944")],
})