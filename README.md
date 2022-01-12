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

```
    float temp = this.speedX;
    this.speedX = ball.speedX;
    ball.speedX = temp;

    float temp2 = this.speedY;
    this.speedY = ball.speedY;
    ball.speedY = temp2;

    double m = 1;

    if(ball.x-this.x != 0) {
        m = (ball.y - this.y)/(ball.x - this.x);
    }
    else {
        m = (ball.y - this.y)/((ball.x - this.x)+0.0001);
    }

    double c = ball.y - (m*ball.x);
    double alfa = Math.atan(m);
    double a = (double)(radius * Math.cos(alfa));
    double x = this.x + (ball.x - this.x)/2;

    //  System.out.println("a: " + a);
    //  System.out.println("x: " + x);

    if(this.x<ball.x) {
        this.x = (float)(x-a);
        this.y = (float)(fungsiL(m, c, this.x));
        
        ball.x = (float)(x+a);
        ball.y = (float)(fungsiL(m, c, ball.x));
    }
    else {
        this.x = (float)(x+a);
        this.y = (float)(fungsiL(m, c, this.x));
        
        ball.x = (float)(x-a);
        ball.y = (float)(fungsiL(m, c, ball.x));
    }
```

Selanjutnya di dalam if condition tadi, dilakukan pertukaran kecepatan x, y axis dari ```this.ball``` (bola 1) terhadap ```ball``` (bola 2). Selanjutnya menggunakan rumus geometri matematika, untuk menentukan titik pusat baru setelah dua bola mengalami tumbukan.

![alt text](https://github.com/mufis-coder/M-Project-DynamicMultipleBouncingBall/blob/main/doc/cara-kerja.jpg) <br />

Pada gambar di atas terlihat, ketika dua bola mengalami tumbukan maka cari titik pusat baru untuk ```this.ball``` dan ```ball```. 

- Cari garis lurus yang melewati dua titik pusat lingkaran -> y = m*x + c.
- Cari besar sudut (alfa) dari garis tersebut.
- Cari titik tengah x-axis dari pusat dua bola tersebut. Simpan pada variabel ```double x```.
- Menggunakan alfa yang telah dicari, cari jarak titik pusat dengan ```double x```. Simpan pada variabel ```double a```.
- Setelah itu sesuaikan titik pusat (x, y) baru dari kedua bola. Menggunakan nilai ```double x``` dan ```double a``` cari nilai x baru. Selanjutnya cari nilai y baru menggunakan rumus garis lurus yang telah dicari dan menggunakan nilai x baru yang sebelumnya telah dicari.


## Demo Game