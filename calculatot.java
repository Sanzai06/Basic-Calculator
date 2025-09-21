<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Modern Colorful Calculator</title>
  <style>
    body {
      background: linear-gradient(135deg, #6a11cb, #2575fc);
      font-family: 'Arial', sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    .calculator {
      background: #1e1e1e;
      padding: 20px;
      border-radius: 20px;
      box-shadow: 0 8px 30px rgba(0,0,0,0.5);
      width: 360px;
    }

    .display {
      width: 100%;
      height: 70px;
      background: #111;
      color: #00ff99;
      font-size: 36px;
      text-align: right;
      padding: 15px;
      border: none;
      border-radius: 12px;
      margin-bottom: 15px;
      box-shadow: inset 0 4px 10px rgba(0,0,0,0.7);
    }

    .buttons {
      display: grid;
      grid-template-columns: repeat(4, 1fr);
      gap: 10px;
    }

    button {
      font-size: 24px;
      padding: 18px;
      border: none;
      border-radius: 12px;
      cursor: pointer;
      transition: 0.2s all;
      color: #fff;
      box-shadow: 0 4px 10px rgba(0,0,0,0.4);
    }

    /* Number buttons */
    .number {
      background: #4CAF50;
    }
    .number:hover {
      background: #45a049;
      transform: translateY(-2px);
    }

    /* Function buttons */
    .func {
      background: #FF5722;
    }
    .func:hover {
      background: #e64a19;
      transform: translateY(-2px);
    }

    /* Wide buttons */
    .wide {
      grid-column: span 2;
    }
  </style>
</head>
<body>
  <div class="calculator">
    <input type="text" id="display" class="display" disabled>
    <div class="buttons">
      <button class="number">7</button>
      <button class="number">8</button>
      <button class="number">9</button>
      <button class="func">/</button>

      <button class="number">4</button>
      <button class="number">5</button>
      <button class="number">6</button>
      <button class="func">*</button>

      <button class="number">1</button>
      <button class="number">2</button>
      <button class="number">3</button>
      <button class="func">-</button>

      <button class="number">0</button>
      <button class="func">.</button>
      <button class="func">=</button>
      <button class="func">+</button>

      <button class="func">C</button>
      <button class="func">←</button>
    </div>
  </div>

  <script>
    const display = document.getElementById('display');
    let num1 = null, operator = null, reset = false;

    document.querySelectorAll('.number').forEach(btn => {
      btn.addEventListener('click', () => {
        if (reset) { display.value = ''; reset = false; }
        display.value += btn.textContent;
      });
    });

    document.querySelectorAll('.func').forEach(btn => {
      btn.addEventListener('click', () => {
        const val = btn.textContent;

        if (['+','-','*','/'].includes(val) && display.value !== '') {
          num1 = parseFloat(display.value);
          operator = val;
          reset = true;
        }
        else if (val === '=') {
          if (operator && display.value !== '') {
            const num2 = parseFloat(display.value);
            let result;
            switch(operator) {
              case '+': result = num1 + num2; break;
              case '-': result = num1 - num2; break;
              case '*': result = num1 * num2; break;
              case '/': 
                result = (num2 === 0) ? 'Error' : num1 / num2;
                break;
            }
            display.value = result;
            num1 = result;
            operator = null;
            reset = true;
          }
        }
        else if (val === 'C') {
          display.value = '';
          num1 = null; operator = null;
        }
        else if (val === '←') {
          display.value = display.value.slice(0,-1);
        }
        else if (val === '.') {
          if (!display.value.includes('.')) display.value += '.';
        }
      });
    });
  </script>
</body>
</html>
