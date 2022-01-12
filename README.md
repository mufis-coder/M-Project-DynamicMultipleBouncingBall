# Dynamic Multiple Bouncing Ball

Game ini sebenernya adalah tugas mingguan dari mata kuliah PBO, tetapi karena ada konsep matematika yang menarik pada game ini. Maka, saya dokumentasikan game ini pada repo ini.

## Cara Kerja Matematika Game

- Terdapat fungsi ```collide(Ball ball)``` pada file ./src/com/tugas/Ball.java. Fungsi tersebut berguna untuk mengecek apakah dua bola mengalami bertabrakan atau tidak.

```
double deltaX = Math.abs(this.x - ball.x);
double deltaY = Math.abs(this.y - ball.y);
double distance = deltaX * deltaX + deltaY * deltaY;
if (distance < (this.radius + ball.radius) * (this.radius + ball.radius)){
    // do something
}
```

Di dalam fungsi tersebut terdapat if condition untuk mengecek apakah dua bola bertabrakan atau tidak. Menggunakan konsep jika jarak antar dua pusat bola, nilainya kurang dari hasil jumlah kedua radius bola. Maka bisa dipastikan kedua bola mengalami tabrakan.

## Demo Game