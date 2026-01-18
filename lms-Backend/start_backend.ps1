$ErrorActionPreference = "Stop"

# ====== CHANGE THIS PATH ONLY IF YOUR PROJECT LOCATION IS DIFFERENT ======
$BASE_PATH = "C:\Users\pramu\Downloads\GOKS PROJECT\loan-management-system\lms-Backend\lms-Backend"

function Start-ServiceWindow {
    param (
        [string]$Path,
        [string]$Name,
        [int]$WaitSeconds = 5
    )

    Write-Host "Starting $Name..." -ForegroundColor Cyan

    if (-Not (Test-Path $Path)) {
        Write-Error "Path not found: $Path"
        return
    }

    Push-Location $Path
    try {
        # Build project if JAR not present
        if (-Not (Test-Path "target")) {
            Write-Host "Building $Name (first time)..." -ForegroundColor Yellow
            .\mvnw.cmd clean package -DskipTests
        }

        $jar = Get-ChildItem -Path "target" -Filter "*.jar" | Sort-Object LastWriteTime -Descending | Select-Object -First 1

        if ($null -eq $jar) {
            Write-Error "No JAR file found in $Path\target"
            return
        }

        Start-Process java `
            -ArgumentList "-jar", $jar.FullName `
            -WorkingDirectory $PWD `
            -WindowStyle Normal

        Write-Host "$Name launched. Waiting $WaitSeconds seconds..." -ForegroundColor Green
        Start-Sleep -Seconds $WaitSeconds
    }
    catch {
        Write-Error "Failed to start $Name : $_"
    }
    finally {
        Pop-Location
    }
}

Write-Host "==========================================" -ForegroundColor Yellow
Write-Host "   Starting LMS Backend Microservices     " -ForegroundColor Yellow
Write-Host "==========================================" -ForegroundColor Yellow

# ====== START SERVICES IN CORRECT ORDER ======

Start-ServiceWindow -Path "$BASE_PATH\config-server" -Name "Config Server" -WaitSeconds 15
Start-ServiceWindow -Path "$BASE_PATH\eureka-server" -Name "Eureka Server" -WaitSeconds 15
Start-ServiceWindow -Path "$BASE_PATH\api-gateway" -Name "API Gateway" -WaitSeconds 10

Start-ServiceWindow -Path "$BASE_PATH\auth-service" -Name "Auth Service" -WaitSeconds 5
Start-ServiceWindow -Path "$BASE_PATH\lms-loan-service" -Name "Loan Service" -WaitSeconds 5
Start-ServiceWindow -Path "$BASE_PATH\paymentservice" -Name "Payment Service" -WaitSeconds 5
Start-ServiceWindow -Path "$BASE_PATH\transactionhistoryservice" -Name "Transaction History Service" -WaitSeconds 5

Write-Host "==========================================" -ForegroundColor Green
Write-Host " ALL BACKEND SERVICES LAUNCHED SUCCESSFULLY" -ForegroundColor Green
Write-Host "==========================================" -ForegroundColor Green
Write-Host "Eureka Dashboard: http://localhost:8761"
Write-Host "Press any key to exit launcher (services will keep running)..."

$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
