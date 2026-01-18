$services = @(
    "config-server",
    "eureka-server",
    "api-gateway",
    "auth-service",
    "lms-loan-service",
    "paymentservice",
    "transactionhistoryservice"
)

foreach ($service in $services) {
    Write-Host "Building $service..."
    Push-Location $service
    try {
        if (Test-Path "mvnw.cmd") {
            & .\mvnw.cmd clean compile -DskipTests | Out-Null
        } else {
             mvn clean compile -DskipTests | Out-Null
        }
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "$service: SUCCESS" -ForegroundColor Green
        } else {
            Write-Host "$service: FAILED" -ForegroundColor Red
        }
    } catch {
        Write-Host "$service: ERROR (Exception)" -ForegroundColor Red
    }
    Pop-Location
}
