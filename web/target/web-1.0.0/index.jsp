<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Management System - DASI</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
  <style>
    :root {
      --primary-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      --secondary-gradient: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
      --success-gradient: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
      --warning-gradient: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
      --dark-gradient: linear-gradient(135deg, #434343 0%, #000000 100%);
    }

    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Poppins', sans-serif;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      overflow-x: hidden;
    }

    .hero-section {
      min-height: 100vh;
      display: flex;
      align-items: center;
      position: relative;
    }

    .hero-section::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 1000"><polygon fill="%23ffffff08" points="0,1000 1000,0 1000,1000"/></svg>');
      pointer-events: none;
    }

    .main-title {
      font-size: 3.5rem;
      font-weight: 700;
      color: white;
      text-shadow: 0 4px 20px rgba(0,0,0,0.3);
      margin-bottom: 1rem;
      animation: fadeInUp 1s ease-out;
    }

    .subtitle {
      font-size: 1.2rem;
      color: rgba(255,255,255,0.9);
      margin-bottom: 2rem;
      animation: fadeInUp 1s ease-out 0.2s both;
    }

    .team-info {
      background: rgba(255,255,255,0.1);
      backdrop-filter: blur(10px);
      border-radius: 15px;
      padding: 1.5rem;
      margin-bottom: 3rem;
      border: 1px solid rgba(255,255,255,0.2);
      animation: fadeInUp 1s ease-out 0.4s both;
    }

    .team-info h3 {
      color: white;
      font-weight: 600;
      margin-bottom: 0.5rem;
    }

    .team-info p {
      color: rgba(255,255,255,0.8);
      margin: 0;
    }

    .management-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: 2rem;
      margin-top: 3rem;
    }

    .management-card {
      background: rgba(255,255,255,0.95);
      border-radius: 20px;
      padding: 2rem;
      text-decoration: none;
      color: inherit;
      transition: all 0.3s ease;
      border: none;
      box-shadow: 0 10px 30px rgba(0,0,0,0.1);
      position: relative;
      overflow: hidden;
      animation: fadeInUp 1s ease-out calc(0.6s + var(--delay)) both;
    }

    .management-card::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      height: 4px;
      background: var(--card-gradient);
      transition: height 0.3s ease;
    }

    .management-card:hover {
      transform: translateY(-10px);
      box-shadow: 0 20px 40px rgba(0,0,0,0.2);
      text-decoration: none;
      color: inherit;
    }

    .management-card:hover::before {
      height: 100%;
      opacity: 0.1;
    }

    .card-icon {
      width: 70px;
      height: 70px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-bottom: 1.5rem;
      background: var(--card-gradient);
      color: white;
      font-size: 1.8rem;
    }

    .card-title {
      font-size: 1.4rem;
      font-weight: 600;
      margin-bottom: 0.8rem;
      color: #333;
    }

    .card-description {
      color: #666;
      line-height: 1.6;
      margin-bottom: 1.5rem;
    }

    .card-arrow {
      color: #999;
      font-size: 1.2rem;
      transition: all 0.3s ease;
    }

    .management-card:hover .card-arrow {
      transform: translateX(5px);
      color: #333;
    }

    /* Gradientes específicos para cada card */
    .card-clientes {
      --card-gradient: var(--primary-gradient);
      --delay: 0s;
    }

    .card-hoteles {
      --card-gradient: var(--secondary-gradient);
      --delay: 0.1s;
    }

    .card-reservas {
      --card-gradient: var(--success-gradient);
      --delay: 0.2s;
    }

    .card-habitaciones {
      --card-gradient: var(--warning-gradient);
      --delay: 0.3s;
    }

    @keyframes fadeInUp {
      from {
        opacity: 0;
        transform: translateY(30px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    @media (max-width: 768px) {
      .main-title {
        font-size: 2.5rem;
      }

      .management-grid {
        grid-template-columns: 1fr;
        gap: 1.5rem;
      }

      .management-card {
        padding: 1.5rem;
      }
    }

    .floating-shapes {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      overflow: hidden;
      pointer-events: none;
    }

    .shape {
      position: absolute;
      background: rgba(255,255,255,0.1);
      border-radius: 50%;
      animation: float 6s ease-in-out infinite;
    }

    .shape:nth-child(1) {
      width: 80px;
      height: 80px;
      top: 20%;
      left: 10%;
      animation-delay: 0s;
    }

    .shape:nth-child(2) {
      width: 120px;
      height: 120px;
      top: 60%;
      right: 10%;
      animation-delay: 2s;
    }

    .shape:nth-child(3) {
      width: 60px;
      height: 60px;
      top: 80%;
      left: 20%;
      animation-delay: 4s;
    }

    @keyframes float {
      0%, 100% {
        transform: translateY(0px);
      }
      50% {
        transform: translateY(-20px);
      }
    }
  </style>
</head>
<body>
<div class="floating-shapes">
  <div class="shape"></div>
  <div class="shape"></div>
  <div class="shape"></div>
</div>

<div class="hero-section">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <div class="text-center">
          <h1 class="main-title">
            <i class="fas fa-hotel me-3"></i>
            Hotel Management System
          </h1>
          <p class="subtitle">Sistema integral de gestión hotelera - Trabajo II DASI</p>

          <div class="team-info">
            <h3><i class="fas fa-users me-2"></i>Equipo de Desarrollo</h3>
            <p>Gonzalo Gardón Martín • Álvaro Criado García • Ricardo Trujillo Antiveros</p>
          </div>
        </div>

        <div class="management-grid">
          <a href="/web-1.0.0/web/jsps/clientes" class="management-card card-clientes">
            <div class="card-icon">
              <i class="fas fa-users"></i>
            </div>
            <h3 class="card-title">Gestión de Clientes</h3>
            <p class="card-description">
              Administra la información de los clientes, registros, contactos y historial de reservas.
            </p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="text-muted">Acceder al módulo</span>
              <i class="fas fa-arrow-right card-arrow"></i>
            </div>
          </a>

          <a href="/web-1.0.0/web/jsps/hoteles" class="management-card card-hoteles">
            <div class="card-icon">
              <i class="fas fa-building"></i>
            </div>
            <h3 class="card-title">Gestión de Hoteles</h3>
            <p class="card-description">
              Controla la información de los hoteles, ubicaciones, servicios y características principales.
            </p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="text-muted">Acceder al módulo</span>
              <i class="fas fa-arrow-right card-arrow"></i>
            </div>
          </a>

          <a href="/web-1.0.0/web/jsps/reservas" class="management-card card-reservas">
            <div class="card-icon">
              <i class="fas fa-calendar-check"></i>
            </div>
            <h3 class="card-title">Gestión de Reservas</h3>
            <p class="card-description">
              Maneja las reservas de los clientes, fechas de entrada y salida, y estado de las reservas.
            </p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="text-muted">Acceder al módulo</span>
              <i class="fas fa-arrow-right card-arrow"></i>
            </div>
          </a>

          <a href="/web-1.0.0/web/jsps/habitaciones" class="management-card card-habitaciones">
            <div class="card-icon">
              <i class="fas fa-bed"></i>
            </div>
            <h3 class="card-title">Gestión de Habitaciones</h3>
            <p class="card-description">
              Administra las habitaciones disponibles, tipos, precios y estado de disponibilidad.
            </p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="text-muted">Acceder al módulo</span>
              <i class="fas fa-arrow-right card-arrow"></i>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
<script>
  // Efecto de parallax suave en las formas flotantes
  document.addEventListener('mousemove', (e) => {
    const shapes = document.querySelectorAll('.shape');
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;

    shapes.forEach((shape, index) => {
      const speed = (index + 1) * 0.5;
      const xPos = (x - 0.5) * speed * 50;
      const yPos = (y - 0.5) * speed * 50;
      shape.style.transform = `translate(${xPos}px, ${yPos}px)`;
    });
  });

  // Animación de entrada para las cards
  const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
  };

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.style.animationPlayState = 'running';
      }
    });
  }, observerOptions);

  document.querySelectorAll('.management-card').forEach(card => {
    observer.observe(card);
  });
</script>
</body>
</html>