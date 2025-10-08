# Jenkins Shared Library

This repository contains a small Jenkins Shared Library with a set of reusable pipeline steps implemented as Groovy files under `vars/`.

The goal is to keep simple, well-documented step functions here and expand other functions later with related complex tasks (build orchestration, advanced Docker workflows, multi-branch strategies, artifact promotion, etc.).

## What this repo contains

Current files (top-level):

- `vars/` - directory that holds pipeline step implementations (Groovy scripts used by Jenkins).

Current `vars/` contents:

```
vars/
    buildDockerImage.groovy       # Build a Docker image from workspace
    fetchCode.groovy              # Checkout or fetch source code
    myFunction.groovy             # Example placeholder function
    pushDockerImage.groovy        # Push Docker image to registry
    runDockerContainer.groovy     # Run a container for smoke tests or dev
```

## Suggested repository structure (recommended)

As the library grows, consider the following structure to keep code maintainable and testable:

```
jenkins-shared-lib/
├─ vars/                      # Lightweight pipeline step entrypoints (Groovy files)
├─ src/                       # Library classes and helpers (Groovy/Java packages)
├─ resources/                 # Any supporting templates, scripts, or files
├─ test/                      # Unit tests (e.g., using JenkinsPipelineUnit)
├─ examples/                  # Example Jenkinsfiles showing usage
├─ docs/                      # Additional documentation for complex flows
└─ README.md
```

Why this helps:
- Keep `vars/` functions small and declarative. Move complicated logic to `src/` where it can be unit tested.
- Add `test/` using JenkinsPipelineUnit (or similar) to validate pipeline logic.
- Use `examples/` to demonstrate common pipelines and integration patterns.

## Roadmap / Future work

- Move complex orchestration and helper utilities into `src/` as classes.
- Add unit tests for steps and helpers under `test/`.
- Add example Jenkinsfiles in `examples/` showing real-world usage (mono-repo, multi-branch, PR build, release pipeline).
- Add a `docs/` section with architecture and common patterns.
- In future, each `vars/*.groovy` that requires additional complexity should reference helpers in `src/` rather than embedding complex logic directly. This makes it easier to add related complex tasks across other functions.

## How to use this library in Jenkins

1. Add this repo as a Shared Library in Jenkins (Manage Jenkins → Configure System → Global Pipeline Libraries).
2. In your Jenkinsfile, call steps implemented in `vars/` directly. Example:

```groovy
@Library('my-shared-lib') _

pipeline {
  agent any
  stages {
    stage('Fetch') { steps { script { fetchCode() } } }
    stage('Build') { steps { script { buildDockerImage() } } }
    stage('Push')  { steps { script { pushDockerImage() } } }
  }
}
```

Replace `my-shared-lib` with the name configured in Jenkins.

## Contributing

- Keep functions small. If a function needs to do multiple steps, extract helpers into `src/`.
- Add tests for new behavior under `test/` and include examples under `examples/`.
- Open issues and PRs with clear description and usage examples.

## License

This project does not include a license file by default. Add `LICENSE` if you want to declare one (MIT/Apache/etc.).

---

If you'd like, I can:

- Create the recommended directories (`src/`, `test/`, `examples/`, `docs/`) and add starter files.
- Add a sample unit test wiring using JenkinsPipelineUnit.

Tell me which follow-up you'd like next.

