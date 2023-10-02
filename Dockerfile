#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /temp
#ARG JAR_FILE=build/libs/test-strategy-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

name: Create and publish a Docker image

env:
    REGISTRY: ghcr.io
    IMAGE_NAME: ${{ github.repository }}

jobs:
    build-and-push-image:
        runs-on: ubuntu-latest

    permissions:
        contents: read
        packages: write
    steps:
        - name: Checkout repository
        uses: actions/checkout@v4

        - name: Log in to the Container registry
        uses: docker/login-action@v3
        with:
            registry: ${{ env.REGISTRY }}
            username: ${{ github.actor }}
            password: ${{ secrets.GITHUB_TOKEN }}
        - name: Extract metadata (tags, labels) for Docker
            id: meta
            uses: docker/metadata-action@v5
            with:
                images: ${{ env.REGISTRY }}/${{ env.IMAGE_NAME }}
        - name: Build and push Docker image
        uses: docker/build-push-action@v5
        with:
            context: .
            push: true
            tags: ${{ steps.meta.outputs.tags }}
            labels: ${{ steps.meta.outputs.labels }}
