#!/bin/bash
set -e  # 오류 발생 시 스크립트 중단

# Elasticsearch가 완전히 준비될 때까지 대기
until curl -s http://localhost:9200 > /dev/null; do
    echo 'Waiting for Elasticsearch...'
    sleep 3
done

# 클러스터 상태 확인
until curl -s http://localhost:9200/_cluster/health | grep -q '"status":"green"'; do
    echo 'Waiting for cluster to be ready...'
    sleep 3
done

echo "Cluster is ready. Starting index initialization..."

# 기존 인덱스 삭제
echo "Deleting existing indices..."
curl -X DELETE "localhost:9200/user_search,project_search,workspace_search,cardboard_search,card_search,attachment_search,comment_search"

# 각 인덱스 생성 함수
create_index() {
    local index_name=$1
    echo "Creating ${index_name} index..."
    if curl -X PUT "localhost:9200/${index_name}" -H 'Content-Type: application/json' -d "@/usr/share/elasticsearch/config/indices/${index_name}.json"; then
        echo "${index_name} created successfully"
    else
        echo "Failed to create ${index_name}"
        exit 1
    fi
}

# 각 인덱스 생성
create_index "user_search"
create_index "project_search"
create_index "workspace_search"
create_index "cardboard_search"
create_index "card_search"
create_index "attachment_search"
create_index "comment_search"

echo "All indices created successfully!"
echo "Created indices:"
curl 'http://localhost:9200/_cat/indices?v'